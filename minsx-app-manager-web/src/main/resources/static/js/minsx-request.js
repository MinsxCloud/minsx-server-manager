let instance = axios.create({
    baseURL: ''
});

const MinsxRequest = {
    get(url, param) {
        return instance.get(url, {params: param});
    },
    delete(url, param) {
        return instance.delete(url, {params: param});
    },
    post(url, body, param) {
        return instance.post(url, body, {params: param});
    },
    postJson(url, body, param) {
        let option = {
            headers: {'Content-Type': 'application/json'}
        };
        if (param) option.params = param;
        return instance.post(url, body, option);
    },
    put(url, body, param) {
        return instance.put(url, body, {params: param});
    },
    putJson(url, body, param) {
        let option = {
            headers: {'Content-Type': 'application/json'}
        };
        if (param) option.params = param;
        return instance.put(url, body, option);
    }
}