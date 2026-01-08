# Traffic Light Controller API

##overview
This project implements a Traffic Light Controller for a single intersection using SpringBoot.

## Features
-Manage traffic lights for four  directions
-Prevents conflicting green singles
-Pause and resume functionality
-Maintains state history
-Thread-safe design

##Design Decisions
-Enum-based state for clarity
-service layer holds all business logic
-Tests written to describe behavior