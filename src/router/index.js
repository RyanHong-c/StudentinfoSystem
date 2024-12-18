//导入vue-router
import { createRouter, createWebHistory } from 'vue-router'
//导入组件
import LoginVue from '@/views/Login.vue'
import LayoutVue from '@/views/Layout.vue'
import StudentCategoryVue from '@/views/student/StudentCategory.vue'
import StudentManageVue from '@/views/student/StudentManage.vue'
import UserAvatarVue from '@/views/user/UserAvatar.vue'
import UserInfoVue from '@/views/user/UserInfo.vue'
import UserResetPasswordVue from '@/views/user/UserResetPassword.vue'

//定义路由关系
const routes = [
    { path: '/login', component: LoginVue },
    {
        path: '/',
        component: LayoutVue,
        //重定向
        redirect: '/student/manage',
        //子路由
        children: [
            { path: '/student/category', component: StudentCategoryVue },
            { path: '/student/manage', component: StudentManageVue },
            { path: '/user/info', component: UserInfoVue },
            { path: '/user/avatar', component: UserAvatarVue },
            { path: '/user/password', component: UserResetPasswordVue },
        ]
    }
]

//创建路由器
const router = createRouter({
    history: createWebHistory(),
    routes: routes
});

export default router