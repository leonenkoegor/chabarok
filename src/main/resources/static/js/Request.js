export default class Request {
    constructor(method, url, params = [],xhrType = null) {
        this.method = method;
        this.url = url;
        this.params = params;
        this.xhrType = xhrType;
    }

    sendRequest(callback) {
        const xhr = new XMLHttpRequest()

        if ((this.method === 'GET')) {
            let body = "?";
            this.params.forEach((value) => {
                body += value + "&";
            })
            body = body.substring(0, body.length - 1);
            xhr.open(this.method, this.url + body);
        } else {
            xhr.open(this.method, this.url)
        }

        if(this.xhrType !== null){
            xhr.responseType = 'json';
        }
        xhr.onload = () => {
            callback(xhr.response);
        }

        if (this.method === 'POST') {
            let body = "";
            this.params.forEach((value) => {
                body += value + "&";
            })
            body = body.substring(0, body.length - 1);
            xhr.send(body);
        } else {
            xhr.send();
        }
    }

}
