pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
        maven { url = uri("https://jitpack.io") }
    }
}



rootProject.name = "FishDiseasesApp"
include(":app")
 