import Vue from 'vue'
import Router from 'vue-router'
// in development env not use Lazy Loading,because Lazy Loading too many pages will cause webpack hot update too slow.so only in production use Lazy Loading
/* layout */
import Layout from '../views/layout/Layout'

const _import = require('./_import_' + process.env.NODE_ENV)
Vue.use(Router)
export const constantRouterMap = [
  {path: '/login', component: _import('login/index'), hidden: true},
  {path: '/404', component: _import('404'), hidden: true},
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    name: '首页',
    hidden: true,
    meta: {title: '报表盘', icon: 'report_plate'},
    children: [{
      path: 'dashboard', component: _import('dashboard/index'),
    }]
  },
  {
    path: '/assets',
    component: Layout,
    redirect: '/assets/',
    name: '资产管理',
    meta: {title: '资产管理', icon: 'assets'},
    children: [
      {
        path: '',
        name: '楼栋管理',
        component: _import('assets/building'),
        meta: {title: '楼栋管理', icon: 'building'},
        menu: 'building'
      },
      {
        path: 'house',
        name: '房屋管理',
        component: _import('assets/house'),
        meta: {title: '房屋管理', icon: 'house'},
        menu: 'house'
      },
    ]
  },
  {
    path: '/system',
    component: Layout,
    redirect: '/system/owner',
    name: '功能模块',
    meta: {title: '业主管理', icon: 'owner'},
    children: [
      {
        path: 'owner',
        name: '业主',
        component: _import('owner/owner'),
        meta: {title: '业主管理', icon: 'owner'},
        menu: 'owner'
      },
    ]
  },
  {
    path: '/cost',
    component: Layout,
    redirect: '/cost/',
    name: '费用管理',
    meta: {title: '费用管理', icon: 'cost'},
    children: [
      {
        path: '',
        name: '收费项目',
        component: _import('cost/charge'),
        meta: {title: '收费项目', icon: 'charge'},
        menu: 'charge'
      },
      {
        path: 'supply',
        name: '收费明细',
        component: _import('cost/chargeDetail'),
        meta: {title: '收费明细', icon: 'charge_detail'},
        menu: 'chargeDetail'
      },
    ]
  },
  {
    path: '/convenience',
    component: Layout,
    redirect: '/convenience/',
    name: '便民服务',
    meta: {title: '便民服务', icon: 'convenience'},
    children: [
      {
        path: '',
        name: '活动管理',
        component: _import('convenience/activity'),
        meta: {title: '活动管理', icon: 'activity'},
        menu: 'activity'
      },
      {
        path: 'supply',
        name: '供求管理',
        component: _import('convenience/supply'),
        meta: {title: '供求管理', icon: 'supply'},
        menu: 'supply'
      },
    ]
  },
  {
    path: '/community',
    component: Layout,
    redirect: '/community/',
    name: '社区服务',
    meta: {title: '社区服务', icon: 'community'},
    children: [
      {
        path: '',
        name: '公告管理',
        component: _import('community/notice'),
        meta: {title: '公告管理', icon: 'notice'},
        menu: 'notice'
      },
      {
        path: 'complaint',
        name: '投诉管理',
        component: _import('community/complaint'),
        meta: {title: '投诉管理', icon: 'complaint'},
        menu: 'complaint'
      },
      {
        path: 'repair',
        name: '维修管理',
        component: _import('community/repair'),
        meta: {title: '维修管理', icon: 'repair'},
        menu: 'repair'
      },
    ]
  },
]
export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({y: 0}),
  routes: constantRouterMap
})
export const asyncRouterMap = [
  {
    path: '/user',
    component: Layout,
    redirect: '/user/',
    name: '',
    meta: {title: '用户权限', icon: 'user_permissions'},
    children: [
      {
        path: '',
        name: '用户列表',
        component: _import('user/user'),
        meta: {title: '用户列表', icon: 'user'},
        menu: 'user'
      },
      {
        path: 'role',
        name: '权限管理',
        component: _import('user/role'),
        meta: {title: '权限管理', icon: 'password'},
        menu: 'role'
      },
    ]
  },
  {path: '*', redirect: '/404', hidden: true}
]
