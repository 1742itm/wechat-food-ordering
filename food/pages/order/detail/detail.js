const app = getApp()
const fetch = app.fetch

Page({
  data: {
    order: {}
  },

  onLoad(options) {
    wx.showLoading({
      title: '努力加载中'
    })
    fetch('/food/getOrderById', {
      id: options.order_id
    }, 'POST').then(data => {
      console.log('后端返回数据:', data)
      this.setData({ order: data })
      wx.hideLoading()
    }, () => {
      this.onLoad(options)
    })
  },

  goHome() {
    wx.switchTab({
      url: '/pages/index/index'
    })
  },

  goOrderList() {
    wx.switchTab({
      url: '/pages/order/list/list'
    })
  },

  goCommentList() {
    wx.navigateTo({
      url: '/pages/order/comment-list/comment-list'
    })
  }
})