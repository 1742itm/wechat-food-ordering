const defaultAvatar = '/images/avatar.png'
const app = getApp()
const fetch = app.fetch

Page({
  data: {
    avatarUrl: defaultAvatar,
    points: 0,
    list: []
  },

  onLoad: function () {
    this.loadData()
  },

  onShow: function () {
    this.loadData()
  },

  onPullDownRefresh: function () {
    this.loadData()
  },

  loadData: function () {
    wx.showLoading({
      title: '努力加载中'
    })
    Promise.all([
      fetch('/food/record'),
      fetch('/points/get')
    ]).then(([recordData, pointsData]) => {
      wx.hideLoading()
      wx.stopPullDownRefresh()
      this.setData({
        ...recordData,
        points: pointsData.points || 0
      })
    }).catch(() => {
      wx.hideLoading()
      wx.stopPullDownRefresh()
    })
  },

  onChooseAvatar: function (e) {
    console.log(e)
    const { avatarUrl } = e.detail
    this.setData({ avatarUrl })
  },

  goToPoints: function () {
    wx.navigateTo({
      url: '/pages/points/points'
    })
  }
})