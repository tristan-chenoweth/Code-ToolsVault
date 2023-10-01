import { createInterface } from "readline";

const rl =
 createInterface({
    input: process.stdin,
    output: process.stdout
})

var userName = 'User';

// userName === 'Jane' ? console.log('Hello, Jane!') : console.log('Hello!, user');

let question = '';
rl.question("What is your question: \n", function (string) {
  question = string;

  var randomNumber = Math.floor(Math.random() * 8 +1);
  // console.log(randomNumber);

  var eightBall = '';

  switch (randomNumber) {
    case 1 :
      eightBall = 'It is certain';
      break;
    case 2 :
      eightBall = 'It is decidedly so';
      break;
    case 3 :
      eightBall = 'Reply hazy try again';
      break;
    case 4 :
      eightBall = 'Cannot predict now';
      break;
    case 5 :
      eightBall = 'Do not count on it';
      break;
    case 6 :
      eightBall = 'My sources say no';
      break;
    case 7 :
      eightBall = 'Outlook not so good';
      break;
    case 8 :
      eightBall = 'Signs point to yes';
      break;
  }

  console.log("Your question was: \"" + question + "\"?");
  console.log(eightBall);
  //var  userQuestion = question + userName;
  // console.log(userQuestion);

  // close input stream
  rl.close();
});

