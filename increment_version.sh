#!/bin/bash

MAJOR="$(grep majorVersion gradle.properties | cut -d = -f2)"
MINOR="$(grep minorVersion gradle.properties | cut -d = -f2)"
PATCH="$(grep patchVersion gradle.properties | cut -d = -f2)"
NEW_PATCH=$((PATCH + 1))

if hash gsed 2>/dev/null; then
  #The default version of sed on OSX is not good, so use gsed (which can be installed via
  #homebrew with 'brew install gnu-sed') if it's available. I'll want to make this
  #a little more robust later on
  gsed -i s/patchVersion=$PATCH/patchVersion=$NEW_PATCH/g gradle.properties
else
  #I'm assuming that this will be gnu-sed. I'll need to make this a little more
  #strict at some point
  sed -i s/patchVersion=$PATCH/patchVersion=$NEW_PATCH/g gradle.properties
fi

git add gradle.properties
git commit --author "CI <james@duckzilla.com>" -m "Updating to version $MAJOR.$MINOR.$NEW_PATCH [skip ci]"
git push origin master
