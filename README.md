# CarDetailingApp

## Relevant information

In this repository you will see 2 branches
1. Master
2. Staging

**Master** is the production branch which is **not to be edited or updated in any way unless absolutely required**.

**Push** and **pull** latest changes from the **staging** branch, we will mainly use Staging for testing purposes. If all the test pass only then **merge** your changes to the **staging** branch.

If there are no issues with the merged staging branch which will include all of our code, we will then push the updated staging into Master.

## Initial Setup

### Initializing Git in Eclipse
Open Terminal in Eclipse
write the following command
```
git init
```
### Connecting the remote repository to eclipse:
```
git remote add origin https://github.com/PranavMistry3/CarDetailingApp.git
```

### Create a new Staging branch:
```
git checkout -b staging
```

### Pull all the latest changes from Staging:
```
git pull origin staging
```
