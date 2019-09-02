
// Problem 1: at the specific point of time, ring our clock.
// Bad code
Calendar c = Calendar.getInstance();
c.setTime(new Date());
c.add(13, 60);      // adds one minute
new AlarmClock().ring(c.getTime());

// Clean code
Instant snoozeTime = now().plusSeconds(60);
ringAfter(snoozeTime);

// End of Problem 1