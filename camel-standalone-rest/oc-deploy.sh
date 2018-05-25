oc login -u=admin -p=admin
oc project my-project
oc delete all --all
oc create configmap camel-rest-props --from-file=./config.json
oc new-app svsvenu/camel-rest:1.0-SNAPSHOT
oc expose svc/camel-rest --hostname=camel-rest-my-project.192.168.64.3.nip.io
oc set env dc/camel-rest --from=configmap/camel-rest-props
oc new-app sebp/elk
oc expose svc/elk --port=5601 --hostname=fabric-elk-rest-my-project.192.168.64.3.nip.io
oc expose svc/elk --name=elk-elastic --port=9200 --hostname=fabric-elastic-rest-my-project.192.168.64.3.nip.io
oc logout