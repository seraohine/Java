import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import Login from '@/views/Login.vue'
import Good from '@/views/Good.vue'
import Customer from '@/views/Customer.vue'
import AboutView from '@/views/AboutView.vue'
import Company from '@/views/Company.vue'
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'login',
      component: Login,
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      // component: () => import('../views/AboutView.vue'),
      component: AboutView,
      children: [
        {
          path: '/good',
          name: 'good',
          component: Good,
        },
        {
          path: '/Company',
          name: 'Company',
          component: Company,
        },
        {
          path: '/customer',
          name: 'customer',
          component: Customer,
        }
      ]
    },
  ],
})

export default router
