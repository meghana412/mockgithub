const nowname = window.location.pathname.substring(1);
const loginName = $('#session-name').text();
const follow_button = new Vue({
    el: '#follow_button',
    data: {
        status: ''
    },
    methods: {
        getStatus: function () {

            fetch(`/api/following/${loginName}/${nowname}`)
                .then(res => res.json())
                .then(res => {
                    console.log(res);
                    if (res === true) {
                        this.status = 'unfollow';
                    } else {
                        this.status = 'follow';
                    }
                })
        },
        changeStatus: function () {

            if (this.status === 'unfollow') {
                fetch(`/api/following/${loginName}/${nowname}`, {
                    method: "DELETE",
                })
                    .then(res => res.json().then(data => ({status: res.status, body: data})))
                    .then(res => {

                        if (res.status === 200 && res.body === true) {
                            this.status = 'follow'
                        }

                    })
            } else {
                fetch(`/api/following/`, {
                    method: "POST",
                    headers:{
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        followerUsername: loginName,
                        followingUsername: nowname
                    })
                })
                    .then(res => res.json().then(data => ({status: res.status, body: data})))
                    .then(res => {
                        if (res.status === 200 && res.body === true) {
                            this.status = 'unfollow'
                        }

                    })
            }
        }
    }
})
follow_button.getStatus();