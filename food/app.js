App({
  fetch: require('./utils/fetch.js'),

  globalData: {
    userInfo: null
  },

  userLoginReady: false,
  userLoginReadyCallback: null,

  onLaunch: function () {
    wx.showLoading({
      title: '登录中',
      mask: true
    })

    this.fetch('/user/checkLogin').then(data => {
      console.log('checkLogin返回数据:', data)
      // 检查数据是否有效
      if (data && data.isLogin !== undefined) {
        if (data.isLogin) {
          console.log('登录状态：已登录（Cookie登录）')
          this.onUserLoginReady()
        } else {
          console.log('登录状态：未登录，开始微信登录')
          this.login({
            success: () => {
              this.onUserLoginReady()
            },
            fail: () => {
              this.onLaunch()
            }
          })
        }
      } else {
        console.log('数据无效，尝试重新登录')
        this.login({
          success: () => {
            this.onUserLoginReady()
          },
          fail: () => {
            this.onLaunch()
          }
        })
      }
    }, () => {
      console.log('请求失败，重试')
      setTimeout(() => {
        this.onLaunch()
      }, 2000)
    })
  },

  login: function (options) {
    wx.login({
      success: res => {
        this.fetch('/user/login', {
          js_code: res.code
        }, 'GET').then(data => {
          console.log('login返回数据:', data)
          // 检查数据是否有效
          if (data && data.isLogin !== undefined) {
            if (data.isLogin) {
              console.log('登录状态：已登录')
              options.success()
            } else {
              wx.hideLoading()
              wx.showModal({
                title: '登录失败',
                confirmText: '重试',
                success: res => {
                  if (res.confirm) {
                    options.fail()
                  }
                }
              })
            }
          } else {
            console.log('登录数据无效')
            wx.hideLoading()
            wx.showModal({
              title: '登录失败',
              confirmText: '重试',
              success: res => {
                if (res.confirm) {
                  options.fail()
                }
              }
            })
          }
        }, () => {
          wx.hideLoading()
          wx.showModal({
            title: '网络请求失败',
            confirmText: '重试',
            success: res => {
              if (res.confirm) {
                options.fail()
              }
            }
          })
        })
      }
    })
  },
  onUserLoginReady: function () {
    wx.hideLoading()
    if (this.userLoginReadyCallback) {
      this.userLoginReadyCallback()
    }
    this.userLoginReady = true
  }
})