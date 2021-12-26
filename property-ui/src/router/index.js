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
    children: [{
      path: 'dashboard', component: _import('dashboard/index')
    }]
  },
  {
    path: '/system',
    component: Layout,
    redirect: '/system/building',
    name: '功能模块',
    meta: {title: '楼栋管理', icon: 'tree'},
    children: [
      {
        path: 'building',
        name: '楼栋',
        component: _import('building/building'),
        meta: {title: '楼栋信息', icon: 'example'},
        menu: 'building'
      },
    ]
  },
  {
    path: '/system',
    component: Layout,
    redirect: '/system/owner',
    name: '功能模块',
    meta: {title: '业主管理', icon: 'tree'},
    children: [
      {
        path: 'owner',
        name: '业主',
        component: _import('owner/owner'),
        meta: {title: '业主信息', icon: 'example'},
        menu: 'owner'
      },
    ]
  },
  {
    path: '/system',
    component: Layout,
    redirect: '/system/activity',
    name: '功能模块',
    meta: {title: '活动管理', icon: 'tree'},
    children: [
      {
        path: 'activity',
        name: '活动',
        component: _import('activity/activity'),
        meta: {title: '活动信息', icon: 'example'},
        menu: 'activity'
      },
    ]
  },
  {
    path: '/system',
    component: Layout,
    redirect: '/system/notice',
    name: '功能模块',
    meta: {title: '公告管理', icon: 'tree'},
    children: [
      {
        path: 'notice',
        name: '公告',
        component: _import('notice/notice'),
        meta: {title: '公告信息', icon: 'example'},
        menu: 'notice'
      },
    ]
  },
  {
    path: '/system',
    component: Layout,
    redirect: '/system/complaint',
    name: '功能模块',
    meta: {title: '投诉管理', icon: 'tree'},
    children: [
      {
        path: 'complaint',
        name: '投诉',
        component: _import('complaint/complaint'),
        meta: {title: '投诉信息', icon: 'example'},
        menu: 'complaint'
      },
    ]
  },{
    path: '/system',
    component: Layout,
    redirect: '/system/repair',
    name: '功能模块',
    meta: {title: '报修管理', icon: 'tree'},
    children: [
      {
        path: 'repair',
        name: '报修',
        component: _import('repair/repair'),
        meta: {title: '报修信息', icon: 'example'},
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
    path: '/system',
    component: Layout,
    redirect: '/system/article',
    name: '功能模块',
    meta: {title: '功能模块', icon: 'tree'},
    children: [
      {
        path: 'article',
        name: '文章',
        component: _import('article/article'),
        meta: {title: '文章', icon: 'example'},
        menu: 'article'
      },
    ]
  },
  {
    path: '/user',
    component: Layout,
    redirect: '/user/',
    name: '',
    meta: {title: '用户权限', icon: 'table'},
    children: [
      {
        path: '', name: '用户列表', component: _import('user/user'), meta: {title: '用户列表', icon: 'user'}, menu: 'user'
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
