# Summary
* This is my caffee/nightmare-fueled testing project completion of a coffeeMaker software provided by Coursera Software Testing Automoation specialization. If you read the discussion forum. Lots of people will say this is impossible to complete. But not me, I am not easily giving up. Anyone who says this project is easy is lying.

* In this project, I tried to crush 2 assignment in a week. Spent 8 hours on the weekend to finish one assignment and the next.

* In this project I use cucumber behaviour-driven development and use Jacoco Coverage Report for the software. it covers 185 test cases with the testing codebase spanning around 600 lines of code.

* One thing I learn from this project is coverage testing is a fantastic way to discover any tiny bugs in your white-box code.

* Another thing I learn from this 48 hours intense project experience is that being a serious software engineer requires you to sit there for 8 or even 12 hours everyday debugging the code and try to discover any small detail of the software. Also, the behavioural way of given..when..and then is a great way to test software.

# The hardest part
* The hardest part is testing the cases one by one finding out which one fail the correct version of the software. And when the project is almost done, polishing the software and having the discipline to go through it and not giving up really tests your drive.

# About journaling for code is good for your soul
* Debugging and polishing by yourself is probably the most tedious and lonliest task you have to do as a software engineer. Developing, testing, debugging, submitting, finding out there is debug and error, and test, debug, submit, and find out more bugs... It can be hard on the soul even if you are passionate about this profession.

* I discover a way to deal with it. Have a small README journal on your repo of the codebase. First of all, you should always have a github repo for all of your project. The reason is of the split moment of you realize you did something wrong and need to rewind your code or find out what you comment out so you can recover it. Not having this ability will slow you down by ten folds.

* One thing I want to discover is how to generate mutant version and test your code against it. It isn't taught in this project but I am sure there is resource online that teaches you that.

# IMPORTANT: DEBUG STEPS
1. look at tracestack and find out the source of your bug from your **own code**, and see if you can fix it there
2. Resubmit and see if it fixes the problem
3. see if the building process have problem, restart the IDE if feeling sus.
4. Look at your code closely and see if you can fix your code there, and resubmit
5. rinse and repeat
6. When suspecting you find out the bug, do breakpoint testing and find out the variable value step by step and debug from there, and find out your bug, resubmit and see if it's correct. Repeat this step as much as you can
7. wait patiently for each submit. It reveals valuable information. If you are impatient, you will lose valuable information that costs you at least half an hour around. (This means not resubmitting, and not walking out)
* At the end, you might realize the bug you thought is sus is actually not a bug, in this case, you have to replan your strategy before diving deep again.
* To kill all the mutant. Sometimes you might need to reach 100% coverage
8. When testing, it also means testing some software that you don't have access, meaning it's a blackbox testing. In this case, you need to test the software in a manual blackbox way
9. don't forget to commit after you get a workable version
10. After a day, look back on the comment out code and see maybe some are actually necessary
11. Notice every inconsistency. Let at your local it is passing your version, but when it's on the server, it doesn't pass. It could mean you have hit the gold


# About being flexible
* Sometimes I have the tendency to find out every bug and try to fix every one that I encounter. A note to myself though. Sometimes you should be flexible to circumvent it to reach your goal. In this case, it is the completion of the project.

# Whenever you do test driven development

* compile and read test result
* breakpoint and debug
* submit and read error 
* read tracestack
* rinse and repeat

* IMPORTANT: Whenever you there is a bit list of tests you need to do, don't think you can skip in 2 instead of one by one. No, it's slower

# About business and computer science
* A essence of software engineering is that the money you make depends on how skillful you are and how much time you have.

* The problem with that, is because it's hard to scale. Yes, you can keep taking more courses and doing more projects and work on harder project

* In comparison, the essence of business is that you go around the world and talk to different clients.

* If you scale both out, business will be travelling around the world, being a consultant, going onsite, talk to different stakeholders

* An CS will be sitting at the office, working with more and more complex software, working with more people, thinking about more complex problem. And jumping a bigger, smarter community to solve bigger, harder problem.

### Question about Business and CS
* The question is, can someone hold a job that requires both skill? 
* I know BA and PM needs that. But at the beginning I guess they really can't really increase their craft, because 1. they don't have the total technical background to be given important technical work, and 2. They don't have the authority and seniority to meet with clients and go onsite.
* I had the opportunity to work as a BA for half a year. At that company I was doing implementation, documentation, user training, and user support.
* But the context is within the enterprise software. So the hard skill isn't really transferrable. And the software skill takes a lot longer to manifest.


* How about full on business. In marketing, in human resource, in accounting, in finance.
* Yes, they are all very important part for running a business
* but essentially you are a cot in a machine that helps to make bigger money for someone up top

* To be honest, it is pretty hard rising up the rank as an asian-american in north america. But I am sure you can do that in asia, like Singapore, Hongkong, Korea or Japan.


### The end goal of technology management
* It is like people expects software engineer to be very good, and then transfer them to a new position where they have the authority, but not necessary business skill to manage

### Conclusion?
* For pure software engineer, I guess their path is get into tech lead, and take MBA and transition to a new company at technical management

* What about me? I haven't had the total technical authority. But I can expect I will go a lot smoothier if I can get to the tech lead level.


# Resource
* Test coverage: https://medium.com/@nicklee1/why-test-code-coverage-targets-are-a-bad-idea-1b9b8ef711ef
* PITEST is a mutant testing tool for java
* 

# The advantage of cucumber
* Fast search
* Fast edit
* Fast readability
* Fast delete

# Heuristics
* When you have the moment of "what's wrong here? I have tested everything". Do breakpoint tracing. It will save your life

# About completing a task
* When you think you are about to finish a task, don't trust this feeling. It takes me another 6 hours to get it completes from I feel it is completing soon.

# About serious tech and deadline
* I think, deadline can make tech all means nothing. Working on this project with a tight deadline requires many tech. The grader with docker and compiler and grading system, your code, and a server that can happen big enough load from all around the globe. If any of these components fails your project fails.

# About education
* Taking this course makes me realize, I don't like getting a master in computer science. I like programming, I think it is fantastic because you can create something out of nothing and you can make something good for the world that can reach tens of thousands of people.
* But getting a degree in computer science is a very different thing. It means working on an assignment for 36 hours and still fails. Trying to grasp a complex subject in a very short time, and have no life besides working on your assignment.
* So what's the end of it, I think MOOC will be a strategic move for those who wants both world. **This is my final conclusion**. But to level up my career, I will continue taking professional certificate like PMP, CISA, and Product management.
