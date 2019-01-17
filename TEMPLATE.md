# ubirch template project

Use this template to bootstrap new projects, but also take a look at live projects
where more up-to-date dependencies might be used.

All build configuration is done in [pom.xml](pom.xml) for the super project
as well as the sub modules ([submodule/pom.xml](submodule/pom.xml)). 

1. a module only produces one and only one artefact
2. create a sister module for common code (i.e. libraries)
3. keep dependencies in check, remove unused dependencies

More complex examples are used in the [niomon project](https://github.com/ubirch/niomon) for
a multi module project and in the [ubirch-protocol-java](https://github.com/ubirch/niomon) for 
simple java based single module project.

## Building and CI

Locally the project is built using the `mvn` command:

```bash
# just compile
mvn compile
# compile and test
mvn test
# compile, test, and package
mvn package
```

> Work in branches for new features, bug fixes and add-ons. Create a [pull request](https://help.github.com/articles/about-pull-requests/)
> to merge the changes into master for building and deployment.

The project needs to be added to the GoCD build server, which will do the following:

1. [Pull Requests](https://help.github.com/articles/about-pull-requests/)
   * compiles, tests, and packages the changes of the PR
   * a pull request will not be accepted if any step fails
2. Merge/Push to `master`
   * compiles, tests, and packages the `master` branch
   * tags docker images with `latest` and pushes to docker-hub
   * deploys `latest` services to the `dev` environment
3. *Continue* deployment to `staging` (TBD)
   * deploys the docker images from the last build to the `staging` environment
4. *Continue* deployment to `production`
   * tags docker images as stable and pushes to docker-hub
   * deploys the docker images to the `prod` environment
  
## Code Style

- Use the default code style from IntelliJ IDEA
- when committing, allow reformat and optimize imports
- *use rearrange code with care*, it may break scala logic, check results
- check the hints that IDEA gives you and fix the yellow markers if possible
- use the following copyright profile for automatic updates (use the Copyright plugin)
    - Settings - Editor - Copyright - Copyright profiles
    - Add as "Apache 2.0" and and only update if "ubirch" appears in the text
    - Select as the default profile
    
```text
Copyright (c) $today.year ubirch GmbH

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```    

> Sometimes the [scalastyle parser fails for valid scala code](https://github.com/scala-ide/scalariform/issues/217) which makes it
> necessary to override the parent pom settings:
>   ```xml
> <plugin>
>     <groupId>org.scalastyle</groupId>
>     <artifactId>scalastyle-maven-plugin</artifactId>
>     <version>1.0.0</version>
>     <configuration>
>         <includeTestSourceDirectory combine.self="override">false</includeTestSourceDirectory>
>     </configuration>
>    </plugin>
>    ``` 
## Deployment

The artefacts usually go into two categories, either they are _executable_ or they
are support _libraries_. Our executables usually are dockerized services, so they 
should be packages as such. If a library is only used locally, it does not need to be deployed.

If libraries are useful in general, they may need to be published to maven-central.
An example for a deployable library is our [ubirch-protocol-java](https://github.com/ubirch/ubirch-protocol-java).

## Using the template

- edit all files and replace names and descriptions
- check dependencies
- write a description in [README.md](README.md)
- remove this file

