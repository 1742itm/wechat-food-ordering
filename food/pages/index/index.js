const app = getApp()
const fetch = app.fetch
// 创建音频上下文
const bgm = wx.createInnerAudioContext()

Page({
  data: {
    swiper: [],
    ad: '',
    category: [],
    isMuted: false // 静音状态
  },

  // 跳转列表页
  start() {
    wx.navigateTo({
      url: '/pages/list/list'
    })
  },

  onLoad() {
    // 封装请求首页数据方法
    const loadIndexData = () => {
      wx.showLoading({
        title: '努力加载中',
        mask: true
      })

      // 请求接口 + 捕获错误
      fetch('/food/index')
        .then(response => {
          this.setData({
            swiper: response.img_swiper || [],
            ad: response.img_ad || '',
            category: response.img_category || []
          })
        })
        .catch(() => {
          // 失败提示 + 重试
          wx.showToast({
            title: '加载失败，点击重试',
            icon: 'none',
            duration: 2000
          })
        })
        .finally(() => {
          wx.hideLoading() // 无论成功失败都关闭 loading
        })
    }

    // 登录状态判断
    if (app.userLoginReady) {
      loadIndexData()
    } else {
      // 登录完成后再执行
      app.userLoginReadyCallback = loadIndexData
    }
    
    // 初始化背景音乐
    this.initBgm()
  },
  
  onShow() {
    // 页面显示时，如果不是静音状态就播放
    if (!this.data.isMuted) {
      bgm.play()
    }
  },
  
  onHide() {
    // 页面隐藏时暂停音乐
    bgm.pause()
  },
  
  onUnload() {
    // 页面卸载时销毁音频
    bgm.destroy()
  },
  
  // 初始化背景音乐
  initBgm() {
    // 设置音频源（请将你的音频文件放在项目中，比如 /audio/bgm.mp3）
    bgm.src = '/audio/1.mp3'
    // 循环播放
    bgm.loop = true
    // 设置音量（0-1）
    bgm.volume = 0.5
    
    // 监听音频播放错误
    bgm.onError((res) => {
      console.error('背景音乐播放错误', res.errMsg)
    })
    
    // 开始播放
    bgm.play()
  },
  
  // 切换静音/播放
  toggleMute() {
    const newMuted = !this.data.isMuted
    this.setData({ isMuted: newMuted })
    
    if (newMuted) {
      bgm.pause()
    } else {
      bgm.play()
    }
  }
})