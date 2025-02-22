FROM  bellsoft/liberica-openjre-alpine:21

#Install utilities
RUN apk add curl jq

#workspace
WORKDIR /home/selenium-docker

#add docker-resource and required files to run the test
ADD target/docker-resources /home/selenium-docker
ADD runner.sh runner.sh

COPY src/test/resources/test-data /home/selenium-docker/test-data

#Environment Variables or parameters
#BROWSER
#HUB_HOST
#TEST_SUITE
#THREAD_COUNT

#Run the test
#ENTRYPOINT java -cp 'libs/*' \
#           -Dselenium.grid.enabled=true \
#           -Dselenium.grid.hubHost=${HUB_HOST} \
#           -Dbrowser=${BROWSER} \
#           org.testng.TestNG \
#           -threadcount ${THREAD_COUNT} \
#           test-suites/${TEST_SUITE}
# Fix for windows
RUN dos2unix runner.sh

#Running test through shell runner file
ENTRYPOINT sh runner.sh