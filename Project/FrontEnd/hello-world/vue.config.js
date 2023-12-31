const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave: false,
  devServer: {
    host: 'localhost', // 项目运行的ip
    port: 8888, // 项目运行的端口号/user
    proxy: {
      '/product': {
        target: 'http://118.25.19.55:9901',
        changeOrigin: true
      },
      '/demo': {
        target: 'http://118.25.19.55:9901',
        changeOrigin: true,
        pathRewrite:{
          "^/demo":''
        }
      },
      '/user': {
        target: 'http://118.25.19.55:9901',
        changeOrigin: true,
        pathRewrite:{
          "^/user":''
        }
      },
      '/product': {
        target: 'http://118.25.19.55:9901',
        changeOrigin: true,
        pathRewrite:{
          "^/product":''
        }
      }, 
      
    },

      
  }
})
