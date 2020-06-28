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
      res.send(/*{ id: question.dataValues.id }*/ question.dataValues)).
    catch(err =>
      res.send({ error: err.errors[0].message }));
});

module.exports = router;
