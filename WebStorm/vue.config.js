let proxyObj = {};

proxyObj['/'] = {
    //wbsocket
    ws: false,
    //  目标地址
    target: 'http://localhost:8181',
    //  发送请求头host会被设置target
    changeOrigin: true,
    //  不重写请求地址
    pathWewrite: {
        "^/": '/',
    }
}
proxyObj['/ws'] = {
    ws: true,
    target: 'ws://localhost:8181'
};

module.exports = {
    devServer: {
        host: "localhost",
        port: 8080,
        proxy: proxyObj,
    }
}