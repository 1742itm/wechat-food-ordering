const app = getApp()
const fetch = app.fetch

Page({
  data: {
    points: 0,
    goodsList: [],
    recordList: []
  },

  onLoad: function () {
    this.loadData()
  },

  loadData: function () {
    wx.showLoading({
      title: '加载中'
    })
    Promise.all([
      fetch('/points/get'),
      fetch('/points/goods'),
      fetch('/points/history')
    ]).then(([pointsData, goodsData, historyData]) => {
      wx.hideLoading()
      this.setData({
        points: pointsData.points || 0,
        goodsList: goodsData.list || [],
        recordList: historyData.list || []
      })
    }).catch(() => {
      wx.hideLoading()
    })
  },

  exchange: function (e) {
    const goodsId = parseInt(e.currentTarget.dataset.id)
    const goods = this.data.goodsList.find(item => parseInt(item.id) === goodsId)
    
    if (!goods || goods.stock <= 0) {
      wx.showToast({
        title: '该商品已兑完',
        icon: 'none'
      })
      return
    }

    if (this.data.points < goods.points) {
      wx.showToast({
        title: '积分不足',
        icon: 'none'
      })
      return
    }

    wx.showModal({
      title: '确认兑换',
      content: `确定用${goods.points}积分兑换${goods.name}吗？`,
      success: (res) => {
        if (res.confirm) {
          wx.showLoading({ title: '兑换中' })
          fetch('/points/exchange', { foodId: goodsId }, 'POST').then(data => {
            wx.hideLoading()
            if (data && data.success) {
              wx.showToast({
                title: '兑换成功',
                icon: 'success'
              })
              this.loadData()
            } else {
              wx.showToast({
                title: data && data.message || '兑换失败',
                icon: 'none'
              })
            }
          }).catch(err => {
            wx.hideLoading()
            wx.showToast({
              title: '网络请求失败',
              icon: 'none'
            })
          })
        }
      }
    })
  }
})