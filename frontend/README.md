Frontend
================================================================================

Before the training session, please run the following command to set up the sample code on your local machine.

Participants will work on the practice exercises by expanding on the sample code.

```bash
REPO_URL=https://github.com/yo1000/edu-docs.git
LOCAL_DIR=edu-frontend
REMOTE_DIR=/frontend/sample-code/

git clone --depth 1 --filter=blob:none --no-checkout --branch master $REPO_URL $LOCAL_DIR
cd $LOCAL_DIR
git sparse-checkout set --no-cone $REMOTE_DIR
git checkout master
```

