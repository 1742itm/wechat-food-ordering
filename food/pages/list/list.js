const app = getApp()
const fetch = app.fetch
// 分类高度数组
const categoryPosition = []
// 小球动画
const shopcartAnimate = require('../../utils/shopcartAnimate.js')
Page({
  data: {
    foodList: [],
    promotion: {},
    activeIndex: 0,
    tapIndex: 0,
    cartPrice: 0,
    cartNumber: 0,
    cartList: {},
    // 购物车弹窗
    showCart: false,
    // 小球动画
    cartBall: {
      show: false,
      x: 0,
      y: 0
    }
  },
  disableNextScroll: false,
  shopcartAnimate: null,
  onLoad() {
    this.loadData()
    this.shopcartAnimate = shopcartAnimate('.operate-shopcart-icon', this)
  },
  // 加载菜单数据
  loadData() {
    wx.showLoading({ title: '加载中...' })
    fetch('/food/list').then(res => {
      wx.hideLoading()  
      // ========== 修复核心 ==========
      const data = res || {}
      const promotionList = data.promotion || []
      
      this.setData({
        foodList: data.list || [],
        promotion: promotionList[0] || {k:50, v:10}
      }, () => {
        this.updateCategoryPosition()
      })
    })
  },
  // 更新分类位置信息（用于滚动联动）
  updateCategoryPosition() {
    const query = wx.createSelectorQuery()
    let top = 0
    let height = 0
    query.select('.food').boundingClientRect(rect => {
      top = rect.top
      height = rect.height
    })
    query.selectAll('.food-category').boundingClientRect(res => {
      categoryPosition.length = 0
      res.forEach(rect => {
        categoryPosition.push(rect.top - top - height / 3)
      })
    })
    query.exec()
  },
  // 点击左侧分类
  tapCategory(e) {
    this.disableNextScroll = true
    const index = e.currentTarget.dataset.index
    this.setData({
      activeIndex: index,
      tapIndex: index
    })
  },
  // 右侧滚动监听
  onFoodScroll(e) {
    if (this.disableNextScroll) {
      this.disableNextScroll = false
      return
    }
    const scrollTop = e.detail.scrollTop
    let activeIndex = 0
    categoryPosition.forEach((item, i) => {
      if (scrollTop >= item) {
        activeIndex = i
      }
    })
    if (activeIndex !== this.data.activeIndex) {
      this.setData({ activeIndex })
    }
  },
  // ========== 加入购物车（教材完整版） ==========
  addToCart: function (e) {
    const index = e.currentTarget.dataset.index
    const category_index = e.currentTarget.dataset.category_index
    const food = this.data.foodList[category_index].food[index]
    const cartList = this.data.cartList
    const foodId = food.id

    if (cartList[foodId]) {
      ++cartList[foodId].number
    } else {
      cartList[foodId] = {
        id: food.id,
        name: food.name,
        price: parseFloat(food.price),
        number: 1
      }
    }

    this.setData({
      cartList,
      cartPrice: this.data.cartPrice + cartList[foodId].price,
      cartNumber: this.data.cartNumber + 1
    })

    // 小球动画
    this.shopcartAnimate.start(e)
  },

  // ========== 显示/隐藏购物车 ==========
  showCartList: function () {
    if (this.data.cartNumber > 0) {
      this.setData({
        showCart: !this.data.showCart
      })
    }
  },

  // ========== 购物车加数量 ==========
  cartNumberAdd: function (e) {
    var id = e.currentTarget.dataset.id
    var cartList = this.data.cartList
    ++cartList[id].number
    this.setData({
      cartList: cartList,
      cartNumber: this.data.cartNumber + 1,
      cartPrice: this.data.cartPrice + cartList[id].price
    })
  },

  // ========== 购物车减数量 ==========
  cartNumberDec: function (e) {
    var id = e.currentTarget.dataset.id
    var cartList = this.data.cartList
    if (cartList[id]) {
      var price = cartList[id].price
      if (cartList[id].number > 1) {
        --cartList[id].number
      } else {
        delete cartList[id]
      }
      this.setData({
        cartList: cartList,
        cartNumber: this.data.cartNumber - 1,
        cartPrice: this.data.cartPrice - price
      })
      if (this.data.cartNumber <= 0) {
        this.setData({
          showCart: false
        })
      }
    }
  },

  // ========== 清空购物车 ==========
  cartClear: function () {
    this.setData({
      cartList: {},
      cartNumber: 0,
      cartPrice: 0,
      showCart: false
    })
  },
  // ========== 提交订单，跳转到确认页 ==========
order: function () {
  if (this.data.cartNumber === 0) {
    return
  }
  wx.showLoading({
    title: '正在生成订单'
  })
  // 将对象转换为数组
  var orderArray = []
  for (var key in this.data.cartList) {
    orderArray.push(this.data.cartList[key])
  }
  fetch('/food/createOrder', {
    order: orderArray
  }, 'POST').then(data => {
    wx.navigateTo({
      url: '/pages/order/checkout/checkout?order_id=' + data.order_id
    })
    wx.hideLoading()
  }, () => {
    this.order()
  })
}
})
