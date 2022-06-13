const functions = require("firebase-functions");
const admin = require("firebase-admin");
const serviceAccount = require("./serviceAccountKey.json");

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
});

const db = admin.firestore();
const auth = admin.auth();

const storeUser = (req, res) => {
  auth.getUser(req.body.uid).then((userRecord) => {
    const user = {
      uid: userRecord.uid,
      email: userRecord.email,
    };
    db.collection("users").doc(user.uid).set(user);
    res.status(200).send("User stored successfully.");
  }
  ).catch((error) => console.log(error));
};

module.exports = {
  storeUser: functions.https.onRequest(storeUser),
};
