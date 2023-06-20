import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui';
import 'font-awesome/css/font-awesome.css'
import 'element-ui/lib/theme-chalk/index.css';
import { postRequest } from "./utils/api";
import { putRequest } from "./utils/api";
import { getRequest } from "./utils/api";
import { deleteRequest } from "./utils/api";
import { initMenu } from './utils/menus';
import { downloadRequest } from './utils/download';
import VueParticles from 'vue-particles';

const echarts = require('echarts');
Vue.prototype.$echarts = echarts
Vue.config.productionTip = false
Vue.use(ElementUI, { size: 'small' });
Vue.prototype.postRequest = postRequest;
Vue.prototype.putRequest = putRequest;
Vue.prototype.getRequest = getRequest;
Vue.prototype.deleteRequest = deleteRequest;
Vue.prototype.downloadRequest = downloadRequest;
Vue.use(VueParticles);

router.beforeEach((to, from, next) => {
    if (window.sessionStorage.getItem('tokenStr')) {
        initMenu(router, store);
        if (!window.sessionStorage.getItem('user')) {
            //  判断用户信息是否存在
            return getRequest('/admin/info').then(resp => {
                if (resp) {
                    window.sessionStorage.setItem('user', JSON.stringify(resp));
                    // this.$store.commit('INIT_ADMIN', resp);
                    store.commit("INIT_ADMIN", resp);
                    next();
                }
            });
        }
        next();
    } else {
        if (to.path == '/') {
            next();
        } else {
            next('/?redirect=' + to.path)
        }
    }
})

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')