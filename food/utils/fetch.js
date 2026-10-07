const config = require('./config.js')
const decodeCookie = require('./decodeCookie.js')
var sess = wx.getStorageSync('JSESSIONID')

module.exports = function (path, data = {}, method = "GET") {
  return new Promise((resolve, reject) => {
    wx.request({
      url: config.baseUrl + path,
      method: method,
      data: data,
      header: {
        'Content-Type': 'application/json',
        'Cookie': sess ? 'JSESSIONID=' + sess : ''
      },
      success: res => {
        // 保存 Cookie
        if (res.header['Set-Cookie'] !== undefined) {
          sess = decodeCookie(res.header['Set-Cookie'])['JSESSIONID']
          wx.setStorageSync('JSESSIONID', sess)
        }

        // 服务器异常判断
        if (res.statusCode !== 200) {
          fail('服务器异常', reject)
          return
        }

        // 检查响应数据
        if (res.data === null || res.data === undefined) {
          fail('服务器返回数据为空', reject)
          return
        }

        resolve(res.data)
      },
      fail: (err) => {
        console.error('请求失败:', err)
        fail('网络请求失败，请检查后端服务是否启动', reject)
      }
    })
  })
}

// 请求失败提示弹窗
function fail(title, callback) {
  wx.hideLoading()
  wx.showModal({
    title: title,
    confirmText: '重试',
    success: res => {
      if (res.confirm) {
        callback()
      }
    }
  })
}