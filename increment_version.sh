#!/bin/bash

MAJOR="$(grep majorVersion gradle.properties | cut -d = -f2)"
MINOR="$(grep minorVersion gradle.properties | cut -d = -f2)"
PATCH="$(grep patchVersion gradle.properties | cut -d = -f2)"
NEW_PATCH=$((PATCH + 1))

sed -i ".bak" "s/\(patchVersion=\).*/\1$NEW_PATCH/g" gradle.properties
rm gradle.properties.bak

git add gradle.properties
git commit --author "CI <james@duckzilla.com>" -m "Updating to version $MAJOR.$MINOR.$NEW_PATCH [skip ci]"
git push origin master