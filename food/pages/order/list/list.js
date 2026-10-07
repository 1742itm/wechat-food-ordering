const app = getApp()
const fetch = app.fetch

Page({
  data: {
    is_last: true,
    order: {}
  },
  last_id: 0,
  row: 10,

  loadData: function (options) {
    wx.showNavigationBarLoading()
    fetch('/food/orderlist', {
      last_id: options.last_id,
      row: this.row
    }).then(data => {
      this.last_id = data.last_id
      this.setData({
        is_last: data.list.length < this.row
      }, () => {
        wx.hideNavigationBarLoading()
        options.success(data)
      })
    }, () => {
      wx.hideNavigationBarLoading()
      options.fail()
    })
  },

  onLoad: function () {
    wx.showLoading({
      title: '加载中'
    })
    this.loadData({
      last_id: 0,
      success: data => {
        this.setData({
          order: data.list
        }, () => {
          wx.hideLoading()
        })
      },
      fail: () => {
        this.onLoad()
      }
    })
  },

  onPullDownRefresh: function () {
    wx.showLoading({
      title: '加载中'
    })
    this.loadData({
      last_id: 0,
      success: data => {
        this.setData({
          order: data.list
        }, () => {
          wx.hideLoading()
          wx.stopPullDownRefresh()
        })
      },
      fail: () => {
        this.onLoad()
      }
    })
  },

  onReachBottom: function () {
    if (this.data.is_last) {
      return
    }
    this.loadData({
      last_id: this.last_id,
      success: data => {
        var order = this.data.order
        data.list.forEach(item => {
          order.push(item)
        })
        this.setData({
          order: order
        })
      },
      fail: () => {
        this.onReachBottom()
      }
    })
  },

  detail: function (e) {
    var id = e.currentTarget.dataset.id
    wx.navigateTo({
      url: '/pages/order/detail/detail?order_id=' + id
    })
  }
})