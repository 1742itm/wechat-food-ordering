const app = getApp()
const fetch = app.fetch

Page({
  data: {
    orderList: [],
    showModal: false,
    selectedOrderId: '',
    selectedStar: 0,
    commentContent: ''
  },

  onShow() {
    this.getOrderList()
  },

  onPullDownRefresh() {
    this.getOrderList()
  },

  getOrderList() {
    fetch('/food/getOrderListWithComment', {}, 'GET').then(data => {
      console.log('订单数据:', data)
      if (data && data.list) {
        this.setData({
          orderList: data.list
        })
      }
      wx.stopPullDownRefresh()
    }, () => {
      wx.showToast({
        title: '获取订单失败',
        icon: 'none'
      })
      wx.stopPullDownRefresh()
    })
  },

  showCommentModal(e) {
    const orderId = e.currentTarget.dataset.id
    const hasComment = e.currentTarget.dataset.hascomment === 'true'
    
    if (hasComment) {
      const order = this.data.orderList.find(item => item.id === orderId)
      if (order && order.commentInfo) {
        this.setData({
          showModal: true,
          selectedOrderId: orderId,
          selectedStar: order.commentInfo.star || 5,
          commentContent: order.commentInfo.content || ''
        })
        return
      }
    }
    
    this.setData({
      showModal: true,
      selectedOrderId: orderId,
      selectedStar: 0,
      commentContent: ''
    })
  },

  closeModal() {
    this.setData({
      showModal: false
    })
  },

  stopPropagation() {
    // 阻止事件冒泡
  },

  selectStar(e) {
    const star = parseInt(e.target.dataset.star)
    this.setData({
      selectedStar: star
    })
  },

  inputContent(e) {
    this.setData({
      commentContent: e.detail.value
    })
  },

  submitComment() {
    if (this.data.selectedStar === 0) {
      wx.showToast({
        title: '请选择星级',
        icon: 'none'
      })
      return
    }

    if (!this.data.commentContent.trim()) {
      wx.showToast({
        title: '请输入评价内容',
        icon: 'none'
      })
      return
    }

    wx.showLoading({
      title: '提交中'
    })

    fetch('/food/submitComment', {
      orderId: this.data.selectedOrderId,
      star: this.data.selectedStar,
      content: this.data.commentContent
    }, 'POST').then(() => {
      wx.hideLoading()
      wx.showToast({
        title: '评价成功',
        icon: 'success'
      })
      this.closeModal()
      this.getOrderList()
    }, () => {
      wx.hideLoading()
      wx.showToast({
        title: '提交失败',
        icon: 'none'
      })
    })
  }
})