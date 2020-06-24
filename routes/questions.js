const express = require('express');
const router = express.Router();
const db = require('../config/database');
const Questions = require('../models/Question');


router.get('/', (req, res) => {
  res.send("INDEX QUESTIONS");
});

router.post('/add', (req, res) => {
  //console.log(req.body);
  Questions.create(req.body).
  then(question => res.send(question.dataValues.id)).
  catch(err => res.send({error: err.errors[0].message}));
});



module.exports = router;
