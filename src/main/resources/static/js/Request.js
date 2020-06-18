export default class Request {
    constructor(method, url, data, isJSON = true) {
        this.method = method;
        this.url = url;
        this.data = data;
        this.isJSON = isJSON;
    }

    sendRequest(callback) {
        const xhr = new XMLHttpRequest()

        if (this.isJSON === true) {
            xhr.responseType = 'json';
        }

        xhr.onload = () => {
            callback(xhr.response);
        }

        if (typeof this.data === "string") {
            xhr.open(this.method, this.url)
            xhr.send(this.data);
            return;
        }

        let body = "?";
        for (let key in this.data) {
            body += key + "=" + this.data[key] + "&";
        }
        body = body.substring(0, body.length - 1);

        if (this.method === "GET") {
            xhr.open(this.method, this.url + body);
            xhr.send();
        } else {
            xhr.open(this.method, this.url);
            xhr.send(body);
        }
    }
}
