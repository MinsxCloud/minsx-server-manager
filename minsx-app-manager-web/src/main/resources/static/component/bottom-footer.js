Vue.component('bottom-footer', {
    template: '<div id="footer">' +
    '               {{copyright}}' +
    '           </div>',
    data: function () {
        return {
            copyright: 'Copyright © 2016-2017 minsx.com All rights reserved.'
        }
    },
    methods: {
    }
});