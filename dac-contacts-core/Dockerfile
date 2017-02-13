FROM payara/server-full
MAINTAINER Pedro Arthur <pfernandesvasconcelos@gmail.com>
ENV DOMAIN domain1
ENV DEPLOY ${PAYARA_PATH}/glassfish/domains/${DOMAIN}/autodeploy
ENTRYPOINT $PAYARA_PATH/bin/asadmin start-domain --verbose ${DOMAIN}
ADD target/dac-contacts-core.war  ${DEPLOY}