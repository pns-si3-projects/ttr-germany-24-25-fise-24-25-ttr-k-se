# ttr-germany-24-25-fise-24-25-ttr-k-se
# Contributing  the Ticket to Ride

### Table of Contents

* [How to Contribute](#how-to-contribute)
  * [Ask questions](#ask-questions)
  * [Create an Issue](#create-an-issue)
  * [Issue Lifecycle](#issue-lifecycle)
  * [Submit a Pull Request](#submit-a-pull-request)
  * [Our branching Strategy](#our-branching-strategy)
* [Build from Source](#build-from-source)
* [Source Code Style](#source-code-style)
* [Reference Docs](#reference-docs)


### How to Contribute

#### Ask questions

If you have a question, check Stack Overflow using
[this list of tags](https://stackoverflow.com/questions/tagged/ticket-to-ride). Find an existing discussion, or start a new one if necessary.

If you believe there is an issue, search through
[existing issues](https://github.com/pns-si3-projects/ttr-germany-24-25-fise-24-25-ttr-k-se/issues) trying a few different ways to find discussions, past or current, that are related to the issue.
Reading those discussions helps you to learn about the issue, and helps us to make a
decision.


#### Create an Issue

If you create an issue after a discussion on Stack Overflow, please provide a description
in the issue instead of simply referring to Stack Overflow. The issue tracker is an
important place of record for design discussions and should be self-sufficient.

Once you're ready, create an issue on [GitHub](https://github.com/pns-si3-projects/ttr-germany-24-25-fise-24-25-ttr-k-se).

Many issues are caused by subtle behavior, typos, and unintended configuration. Please create a Minimal reproducible Example of the problem , it will help the team understand better and get down to the root cause of the problem.


#### Issue Lifecycle

When an issue is first created, it is flagged `waiting-for-triage` waiting for a team
member to triage it. Once the issue has been reviewed, the team may ask for further
information if needed, and based on the findings, the issue is either assigned a target
milestone or is closed with a specific status.

When a fix is ready, the issue is closed and may still be re-opened until the fix is
released. After that the issue will typically no longer be reopened. In rare cases if the
issue was not at all fixed, the issue may be re-opened. In most cases however any
follow-up reports will need to be created as new issues with a fresh description.


#### Submit a Pull Request

1. Should you create an issue first? No, just create the pull request and use the
description to provide context and motivation, as you would for an issue. If you want
to start a discussion first or have already created an issue, once a pull request is
created, we will close the issue as superseded by the pull request, and the discussion
about the issue will continue under the pull request.

2. Always check out the `main` branch and submit pull requests against it.
Backports to prior versions will be considered on a case-by-case basis and reflected as
the fix version in the issue tracker.

3. Choose the granularity of your commits consciously and squash commits that represent
multiple edits or corrections of the same logical change.

4. Format commit messages using 55 characters for the subject line, 72 characters per line
for the description.

5. If there is a prior issue, reference the GitHub issue number in the description of the
pull request.

If accepted, your contribution may be heavily modified as needed prior to merging.
You will likely retain author attribution for your Git commits granted that the bulk of
your changes remain intact. You may also be asked to rework the submission.

If asked to make corrections, simply push the changes against the same branch, and your
pull request will be updated. In other words, you do not need to create a new pull request
when asked to make changes.


#### Our branching strategy
We follow a modified version of the Git Flow branching strategy. The main difference is that we do not use a release branch. Here is an overview of our branching strategy:

1. **Main Branch (`main`)**:
   - This is the stable branch that always contains the latest release-ready code.
   - All pull requests should be submitted against the `main` branch.

2. **Develop Branch (`develop`)**:
   - This branch contains the latest development changes.
   - Feature branches are merged into the `develop` branch.
   - Once the `develop` branch is stable and ready for release, it is merged into the `main` branch.

3. **Feature Branches (`feature/*`)**:
   - These branches are used to develop new features.
   - Feature branches are created from the `develop` branch.
   - Once a feature is complete, the feature branch is merged back into the `develop` branch.



#### Participate in Reviews

Helping to review pull requests is another great way to contribute. Your feedback
can help to shape the implementation of new features. When reviewing pull requests,
however, please refrain from approving or rejecting a PR unless you are a core
committer for the Ticket to Ride project.

### Build from Source

To check out, build, and import the ttr-germany-24-25-fise-24-25-ttr-k-se source code into your IDE, you can just clone the repository and execute the following commands:
```sh
    mvn clean package
    mvn test
    mvn exec:java`
```

### Reference Docs
Some usefull refrence docs:
[The ticket to ride ruleboook](https://cdn.1j1ju.com/medias/2c/f9/7f-ticket-to-ride-rulebook.pdf)
