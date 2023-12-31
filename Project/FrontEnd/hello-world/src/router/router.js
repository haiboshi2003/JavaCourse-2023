import { createRouter, createWebHashHistory } from 'vue-router'



const routes = [
    {
        path: '/',
        component: () => import('../components/log-in.vue')
    },
    {
        path: '/jwt',
        component: () => import('../components/jwt.vue')
    },
    {
        path: '/404',
        component: () => import('../components/My404.vue')
        
    },

    {
        path: '/menu',
        component: () => import('../components/MainMenu.vue'),
        redirect: '/menu/addtitle',  //重定向，访问/a3立即跳转到/a3/student
        children: [
            {
                path: 'ProductionInquiry',
                component: () => import('../components/searchtitle.vue')

            },
           
            {
                path: 'addtitle',
                component: () => import('../components/addtitle.vue')
            },
            
           
            
        ]
    },
    {
        path: '/:pathMatcher(.*)*',
        redirect: '/404'
    }
    
]

const router = createRouter({
    //router格式，hash
    history: createWebHashHistory(),
    routes : routes
    

})

export default router;