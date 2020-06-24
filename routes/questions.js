const express = require('express');
const router = express.Router();
const db = require('../config/database');
const Questions = require('../models/Question');


router.get('/', (req, res) => {
  res.send("INDEX QUESTIONS");
});

router.post('/add', (req, res) => {
  Questions.create(req.body).
    then(question =>
      res.send(/*{ id: question.dataValues.id }*/ question.dataValues)).
    catch(err =>
      res.send({ error: err.errors[0].message }));
});



module.exports = router;