# https://developer.ibm.com/code/2017/07/13/step-step-guide-running-gitlab-ce-docker/

sudo docker run --detach --name gitlab \
	--hostname gitlab.example.com \
	--publish 30080:30080 \
         --publish 30022:22 \
	--env GITLAB_OMNIBUS_CONFIG="external_url 'http://gitlab.example.com:30080'; gitlab_rails['gitlab_shell_ssh_port']=30022;" \
	gitlab/gitlab-ce:9.1.0-ce.0

sudo docker run --detach --name gitlab --hostname gitlab.example.com --publish 30080:30080 --publish 30022:22 --env GITLAB_OMNIBUS_CONFIG="external_url 'http://gitlab.example.com:30080'; gitlab_rails['gitlab_shell_ssh_port']=30022;" gitlab/gitlab-ce:9.1.0-ce.0