oc login -u=admin -p=admin
oc delete all --all
oc new-app svsvenu/fabric-camel-rest:1.0-SNAPSHOT
oc expose svc/fabric-camel-rest --hostname=fabric-camel-rest-my-project.192.168.64.3.nip.io
oc logout
