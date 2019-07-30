Vue.directive('highlightjs', {
    deep: true,
    bind: function(el, binding) {
        // on first bind, highlight all targets
        let targets = el.querySelectorAll('code')
        targets.forEach((target) => {
            // if a value is directly assigned to the directive, use this
            // instead of the element content.
            if (binding.value) {
                target.textContent = binding.value
            }
            hljs.highlightBlock(target)
        })
    },
    componentUpdated: function(el, binding) {
        // after an update, re-fill the content and then highlight
        let targets = el.querySelectorAll('code')
        targets.forEach((target) => {
            if (binding.value) {
                target.textContent = binding.value
                hljs.highlightBlock(target)
            }
        })
    }
});

[username, repositoryName] = window.location.pathname.substr(1).split('/')

const app = new Vue({
    el: '#app',
    data: {
        code: "",
        filename: ""
    },
    methods: {
        getCode: function (filename) {

            fetch(`/api/git/code`, {
                method: "POST",
                headers:{
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    username: username,
                    repositoryName: repositoryName,
                    filename: filename
                    // username: "pwxcoo",
                    // repositoryName: "tester",
                    // filename: "happy.java"
                })
            })
                .then(res => res.json().then(data => ({status: res.status, body: data})))
                .then(res => {
                    console.log(res);
                    this.code = res.body.fileContent;
                    this.filename = res.body.filename;
                    hljs.initHighlightingOnLoad();
                })
        },
        test: function () {
            console.log('happy');
            this.code = 'import requests';
        }
    }
})
//    app.getCode();

// demo data
var data = {
    name: `${username}/${repositoryName}`,
    children: [
        { name: 'hello' },
        { name: 'wat' },
        {
            name: 'child folder',
            children: [
                {
                    name: 'child folder',
                    children: [
                        { name: 'hello' },
                        { name: 'wat' }
                    ]
                },
                { name: 'hello' },
                { name: 'wat' },
                {
                    name: 'child folder',
                    children: [
                        { name: 'hello' },
                        { name: 'wat' }
                    ]
                }
            ]
        }
    ]
}

// define the item component
Vue.component('item', {
    template: '#item-template',
    props: {
        model: Object
    },
    data: function () {
        return {
            open: false
        }
    },
    computed: {
        isFolder: function () {
            return this.model.children &&
                this.model.children.length
        }
    },
    methods: {
        toggle: function () {
            if (this.isFolder) {
                this.open = !this.open
            } else {
                app.getCode(this.model.name);
                // console.log();
            }

        },
        changeType: function () {
            if (!this.isFolder) {
                Vue.set(this.model, 'children', [])
                this.addChild()
                this.open = true
            }
        },
        addChild: function () {
            this.model.children.push({
                name: 'new stuff'
            })
        },
        addChild: function (c) {
            this.model.children.push(c);
        }
    }
})

// boot up the demo
var demo = new Vue({
    el: '#demo',
    data: {
        treeData: data
    },
    methods: {
        init: function () {
            fetch(`/api/git/files?username=pwxcoo&repositoryName=tester`)
                .then(res => res.json().then(data => ({status: res.status, body: data})))
                .then(res => {


                    this.treeData.children = [];
                    for (var c in res.body) {
                        console.log(res.body[c]);
                        this.treeData.children.push(res.body[c]);
                    }
//                        console.log(this.treeData.children);
//                        console.log(res.body);
                })

        }
    }
})
demo.init();