#!/bin/sh
dirname="$(dirname "$0")"
. "$dirname/__service_key.sh"
SPRING_PROFILES_ACTIVE=production ./gradlew -DserviceKey=$_service_keyclean test