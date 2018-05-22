Vue.component('top-header', {
    template: '<div id="header">' +
    '               <a href="/overview">' +
    '                   <div class="logo"></div>' +
    '               </a>' +
    '               <div class="title">{{title}}</div>' +
    '         </div>',
    data: function () {
        return {
            title: '应用管理平台（App Manager Platform）'
        }
    },
    methods: {}
});