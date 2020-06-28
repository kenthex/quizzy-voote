const express = require('express');
const router = express.Router();
const db = require('../config/database');
const Questions = require('../models/Question');
const Users = require('../models/User');


router.get('/', (req, res) => {
  Questions.findAll({include: [
      {
        model: Users,
        association: 'user_data',
        foreignKey: 'id',
        attributes: ['login'],
        required: true
        //attributes: [ 'login']
      }
    ]}).
    then(questions => {
      var username = "";
      questions.forEach(question => {
        var date = question.dataValues.expired_at;
        var date = date.getTime();
        question.dataValues.expired_at = date;
        question.dataValues.creator = question.dataValues.user_data.dataValues.login;
      });
      res.send({ questions: questions });
    }).
    catch(err => console.log(err));
});

router.post('/add', (req, res) => {
  console.log(req.body);
  Questions.create(req.body).
    then(question =>
      res.send(question.dataValues)).
    catch(err =>
      res.send({ error: err.errors[0].message }));
});

router.post('/setverified', (req, res) => {
  
  var date = new Date();
  date.setDate(date.getDate() + 3);

  Questions.update(
   { verified: 1, expired_at: date },
   { where: { id: req.body.id } }).
    then(question => res.send(question));
});

module.exports = router;
