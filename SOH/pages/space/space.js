// pages/quest/ques.js
//index.js
//获取应用实例
const app = getApp()

Page({
  data: {

    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    toast1Hidden: true,
    modalHidden: true,
    modalHidden2: true,
    notice_str: '',
    items: [
      { name: 'q_tp04_1_A', value: '喜欢，我乐意与他人相处' },
      { name: 'q_tp04_1_B', value: '无所谓，我对这些不是很关心' },
      { name: 'q_tp04_1_C', value: '不喜欢，我喜欢独处' },
    ],
    items2: [
      { name: 'q_tp04_2_A', value: '外出，包括旅游、运动、购物等' },
      { name: 'q_tp04_2_B', value: '在家里做一切可以休闲的事情' },
      { name: 'q_tp04_2_C', value: '我不喜欢任何休闲方式' },
    ],
    items3: [
      { name: 'q_tp04_3_A', value: '欣赏他的行为' },
      { name: 'q_tp04_3_B', value: '不赞同他的行为' },
      { name: 'q_tp04_3_C', value: '无特别看法或不了解' },
    ],
    items4: [
      { name: 'q_tp04_4_A', value: '活动量大的运动，比如长跑、搏击、普拉提' },
      { name: 'q_tp04_4_B', value: '活动量小的运动，比如瑜伽' },
      { name: 'q_tp04_4_C', value: '不喜欢运动' },
    ],
    items5: [
      { name: 'q_tp04_5_A', value: '经常' },
      { name: 'q_tp04_5_B', value: '偶尔' },
      { name: 'q_tp04_5_C', value: '不玩' },
    ],
    items6: [
      { name: 'q_tp04_6_A', value: '轻松欢快，比如乡村音乐' },
      { name: 'q_tp04_6_B', value: '时尚动感，比如电子音乐' },
      { name: 'q_tp04_6_C', value: '沉重悲伤，比如悲怆鸣奏曲' },
    ],
    items7: [
      { name: 'q_tp04_7_A', value: '会，因为好的事物就该让更多人知道' },
      { name: 'q_tp04_7_B', value: '不会，因为我不想让其他人知道的喜好' },
      { name: 'q_tp04_7_C', value: '视情况而定' },
    ],
    items8: [
      { name: 'q_tp04_8_A', value: '会，因为我喜欢的东西就想做完' },
      { name: 'q_tp04_8_B', value: '不会，因为我不想让其他人知道的喜好' },
      { name: 'q_tp04_8_C', value: '不确定，视情况而定' },
    ],
    items9: [
      { name: 'q_tp04_9_A', value: '外出游玩、购物等' },
      { name: 'q_tp04_9_B', value: '看书、追剧等' },
      { name: 'q_tp04_9_C', value: '睡觉、休息等' },
    ],
    items10: [
      { name: 'q_tp04_10_A', value: '经常幻想，梦想自己彩票中奖' },
      { name: 'q_tp04_10_B', value: '偶尔幻想，但清楚的知道不可能实现' },
      { name: 'q_tp04_10_C', value: '从不幻想，因为这是虚幻没可能实现的' },
    ],

  },

  //单选框选择改变
  radioChange: function (e) {
    console.log('radio发生change事件，携带value值为：', e.detail.value)
  },

  //事件处理函数
  bindViewTap: function () {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  onLoad: function () {
    if (app.globalData.userInfo) {
      this.setData({
        userInfo: app.globalData.userInfo,
        hasUserInfo: true
      })
    } else if (this.data.canIUse) {
      // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
      // 所以此处加入 callback 以防止这种情况
      app.userInfoReadyCallback = res => {
        this.setData({
          userInfo: res.userInfo,
          hasUserInfo: true
        })
      }
    } else {
      // 在没有 open-type=getUserInfo 版本的兼容处理
      wx.getUserInfo({
        success: res => {
          app.globalData.userInfo = res.userInfo
          this.setData({
            userInfo: res.userInfo,
            hasUserInfo: true
          })
        }
      })
    }
  },
  getUserInfo: function (e) {
    console.log(e)
    app.globalData.userInfo = e.detail.userInfo
    this.setData({
      userInfo: e.detail.userInfo,
      hasUserInfo: true
    })
  },
  //弹出框
  toast1Change: function (e) {
    this.setData({ toast1Hidden: true });
  },
  //弹出确认框
  modalTap: function (e) {
    this.setData({
      modalHidden: false
    })
  },
  confirm_one: function (e) {
    console.log(e);
    this.setData({
      modalHidden: true,
      toast1Hidden: false,
      notice_str: '提交成功'
    });
  },
  cancel_one: function (e) {
    console.log(e);
    this.setData({
      modalHidden: true,
      toast1Hidden: false,
      notice_str: '取消成功'
    });
  },
  //弹出提示框
  modalTap2: function (e) {
    this.setData({
      modalHidden2: false
    })
  },
  modalChange2: function (e) {
    this.setData({
      modalHidden2: true
    })
  },
  //更新表单
  formSubmit: function (e) {
    var that = this;
    var formData = e.detail.value;
    wx.request({
      url: 'http://test.com:8080/gj/quesServlet',
      data: formData,
      header: {
        'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
      },
      method: 'POST',
      success: function (res) {
        console.log(e.detail.value);
        console.log(res.data);
        that.modalTap();
      }
    })
  },
  formReset: function () {
    console.log('form发生了reset事件');
    this.modalTap2();
  },
  //弹出框
  toast1Change: function (e) {
    this.setData({ toast1Hidden: true });
  },
  //弹出确认框
  modalTap: function (e) {
    this.setData({
      modalHidden: false
    })
  },
  confirm_one: function (e) {
    console.log(e);
    this.setData({
      modalHidden: true,
      toast1Hidden: false,
      notice_str: '提交成功'
    });
  },
  cancel_one: function (e) {
    console.log(e);
    this.setData({
      modalHidden: true,
      toast1Hidden: false,
      notice_str: '取消成功'
    });
  },
  //弹出提示框
  modalTap2: function (e) {
    this.setData({
      modalHidden2: false
    })
  },
  modalChange2: function (e) {
    this.setData({
      modalHidden2: true
    })
  },
  //更新表单
  formSubmit: function (e) {
    var that = this;
    var formData = e.detail.value;
    wx.request({
      url: 'http://test.com:8080/test/quesServlet',
      data: formData,
      header: {
        'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
      },
      method: 'POST',
      success: function (res) {
        console.log(e.detail.value);
        console.log(res.data);
        that.modalTap();
      }
    })
  },
  formReset: function () {
    console.log('form发生了reset事件');
    this.modalTap2();
  }
})

