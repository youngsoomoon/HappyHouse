import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "@/views/HomeView.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "HomeView",
    component: HomeView,
  },
  {
    path: "/notice",
    name: "NoticeView",
    component: () => import("@/views/NoticeView.vue"),
    redirect: "/notice/list",
    children: [
      {
        path: "list",
        name: "NoticeList",
        component: () => import("@/components/board/notice/NoticeList.vue"),
      },
      {
        path: "detail/:contentId",
        name: "NoticeDetail",
        component: () => import("@/components/board/notice/NoticeDetail"),
      },
      {
        path: "create/:contentId?",
        name: "NoticeCreate",
        component: () => import("@/components/board/notice/NoticeCreate"),
      },
    ],
  },
  {
    path: "/qna",
    name: "QnaView",
    component: () => import("@/views/QnaView.vue"),
    redirect: "/qna/list",
    children: [
      {
        path: "list",
        name: "QnaList",
        component: () => import("@/components/board/qna/QnaList.vue"),
      },
      {
        path: "detail/:contentId",
        name: "QnaDetail",
        component: () => import("@/components/board/qna/QnaDetail"),
      },
      {
        path: "create/:contentId?",
        name: "QnaCreate",
        component: () => import("@/components/board/qna/QnaCreate"),
      },
    ],
  },
  {
    path: "/freeboard",
    name: "FreeBoardView",
    component: () => import("@/views/FreeBoardView.vue"),
    redirect: "/freeboard/list",
    children: [
      {
        path: "list",
        name: "FreeBoardList",
        component: () =>
          import("@/components/board/freeboard/FreeBoardList.vue"),
      },
      {
        path: "detail/:contentId",
        name: "FreeBoardDetail",
        component: () => import("@/components/board/freeboard/FreeBoardDetail"),
      },
      {
        path: "create/:contentId?",
        name: "FreeBoardCreate",
        component: () => import("@/components/board/freeboard/FreeBoardCreate"),
      },
    ],
  },
  {
    path: "/usermanagement",
    name: "UserManagementView",
    component: () => import("@/views/UserManagementView.vue"),
    redirect: "/usermanagement/list",
    children: [
      {
        path: "list",
        name: "UserList",
        component: () => import("@/components/admin/UserList.vue"),
      },
      {
        path: "detail/:userId",
        name: "UserDetail",
        component: () => import("@/components/admin/UserDetail.vue"),
      },
    ],
  },
  {
    path: "/user",
    name: "UserView",
    component: () => import("@/views/UserView.vue"),
    redirect: "/",
    children: [
      {
        path: "login",
        name: "UserLogin",
        component: () => import("@/components/user/UserLogin.vue"),
      },
      {
        path: "register",
        name: "UserRegister",
        component: () => import("@/components/user/UserRegister.vue"),
      },
      {
        path: "mypage",
        name: "UserMyPage",
        // beforeEnter: onlyAuthUser,
        component: () => import("@/components/user/UserMyPage.vue"),
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
