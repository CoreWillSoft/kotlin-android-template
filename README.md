# kotlin-android-template 🤖

[![Use this template](https://img.shields.io/badge/from-kotlin--android--template-brightgreen?logo=dropbox)](https://github.com/corewillsoft/kotlin-android-template/generate) ![Pre Merge Checks](https://github.com/corewillsoft/kotlin-android-template/workflows/Pre%20Merge%20Checks/badge.svg)  ![License](https://img.shields.io/github/license/cortinico/kotlin-android-template.svg) ![Language](https://img.shields.io/github/languages/top/cortinico/kotlin-android-template?color=blue&logo=kotlin)

A simple Github template that lets you create an **Android/Kotlin** project and be up and running in a **few seconds**. 

This template is focused on delivering a project with **static analysis** and **continuous integration** already in place.

## How to use 👣

Just click on [![Use this template](https://img.shields.io/badge/-Use%20this%20template-brightgreen)](https://github.com/corewillsoft/kotlin-android-template/generate) button to create a new repo starting from this template.

Once created don't forget to update the:
- [App ID](buildSrc/src/main/java/Coordinates.kt)
- AndroidManifest ([here](app/src/main/AndroidManifest.xml) and [here](library-android/src/main/AndroidManifest.xml))
- Package of the source files

## Features 🎨

- **100% Kotlin-only template**.
- Husky git pre-hook. To enable run once `npm install`. 
- 3 Sample modules (Android app, Android library, Kotlin library).
- Sample Espresso, Instrumentation & JUnit tests.
- 100% Gradle Kotlin DSL setup.
- Dependency versions managed via `buildSrc`.
- CI Setup with GitHub Actions.
- Kotlin Static Analysis via `ktlint` and `detekt`.
- Publishing Ready.
- Issues Template (bug report + feature request)
- Pull Request Template.
- Libraries update detection

## Useful commands:

- To run units use `./gradlew test`
- To run detekt and klint use `./gradlew check`
- To run format simply run `./gradlew ktlintFormat`
- To check for the dependencies updates use `./gradlew dependencyUpdates`. Good practice to do this at the start of every sprint.

## Husky setup

Run `npm install` once to enable git pre-hook functionality. Check package.json to modify.

## Gradle Setup 🐘

This template is using [**Gradle Kotlin DSL**](https://docs.gradle.org/current/userguide/kotlin_dsl.html) as well as the [Plugin DSL](https://docs.gradle.org/current/userguide/plugins.html#sec:plugins_block) to setup the build.

Dependencies are centralized inside the [Dependencies.kt](buildSrc/src/main/java/Dependencies.kt) file in the `buildSrc` folder. This provides convenient auto-completion when writing your gradle files.

## Static Analysis 🔍

This template is using [**ktlint**](https://github.com/pinterest/ktlint) with the [ktlint-gradle](https://github.com/jlleitschuh/ktlint-gradle) plugin to format your code. To reformat all the source code as well as the buildscript you can run the `ktlintFormat` gradle task.

This template is also using [**detekt**](https://github.com/detekt/detekt) to analyze the source code, with the configuration that is stored in the [detekt.yml](quality/detekt/detekt.yml) file (the file has been generated with the `detektGenerateConfig` task).

## CI ⚙️

This template is using [**GitHub Actions**](https://github.com/corewillsoft/kotlin-android-template/actions) as CI. You don't need to setup any external service and you should have a running CI once you start using this template.

There are currently the following workflows available:
- [Validate Gradle Wrapper](.github/workflows/gradle-wrapper-validation.yml) - Will check that the gradle wrapper has a valid checksum;
- [Pre Merge Checks](.github/workflows/pre-merge.yaml) - Will run the `build`, `check` and `publishToMavenLocal` tasks;
- [Publish Release to firebase](.github/workflows/publish_release.yaml)
    - Will build release on push to the `main` branch;
    - Sign build by the keys from github secrets;
    - Prepare release notes upon commits starting from the last tag;
    - Publish build to the Firebase Distribution;
    - To use:
        - Remove `if: false` line;
        - Configure Secrets for [Signing Properties](https://github.com/r0adkll/sign-android-release);
        - Configure Secrets for [Firebase distribution](https://github.com/wzieba/Firebase-Distribution-Github-Action);
- [Publish Debug to firebase](.github/workflows/publish_debug.yaml)
    - Will build debug on PR from the `release/*` branch;
    - Prepare release notes upon commits starting from the main branch;
    - Publish build to the Firebase Distribution;
    - To use:
        - Remove `if: false` line;
        - Configure Secrets for [Signing Properties](https://github.com/r0adkll/sign-android-release);
        - Configure Secrets for [Firebase distribution](https://github.com/wzieba/Firebase-Distribution-Github-Action);
- [Publish Debug to App Center](.github/workflows/beta-to-testers-app-center.yaml)
    - Will build debug from main branch;
    - Prepare release notes upon commits starting from the last tag;
    - Publish build to the app center;
    - To use:
        - Remove `if: false` line;
        - Create project on the App Center and replace name stubs
        - Create group on the App Center and replace group name stub
        - Configure Secrets for [Signing Properties](https://github.com/r0adkll/sign-android-release);
        - Configure Secrets for [Appcenter distribution](https://github.com/wzieba/AppCenter-Github-Action);
- [Publish Release to App Center](.github/workflows/release-to-internal-google-play.yaml)
    - Will build release from created tag;
    - Publish build to the google play internal testing channel;
    - To use:
        - Remove `if: false` line;
        - Create project on the App Center and replace name stubs
        - Configure Secrets for [Signing Properties](https://github.com/r0adkll/sign-android-release);
        - Generate service key on google play (admin role required) [Google Play service key](https://corewillsoft.atlassian.net/wiki/spaces/GENERAL/pages/562659333/Configure+google+play+API+service+account+for+CI);
        - Configure Secrets for [Google Play Distribution](https://github.com/r0adkll/upload-google-play)
        - Configure Secrets [Appcenter publish mapping file](https://github.com/wzieba/AppCenter-Github-Action);


## Publishing 🚀

The template is setup to be **ready to publish** a library/artifact on a Maven Repository. If you're using JitPack, you don't need any further configuration and you can just configure the repo on JitPack. If you're using another repository (MavenCentral/JCenter/etc.), you need to specify the publishing coordinates.

## Contributing 🤝

Feel free to open a issue or submit a pull request for any bugs/improvements.

## Code of Conduct

### 3rd-party libraries

- favor libs with KMP support
- favor language/platform de-facto standard, e.g.: `java.time`
