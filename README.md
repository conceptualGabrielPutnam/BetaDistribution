# BetaDistribution
Quick Java project for generating a Beta Distribution using a lookup table.

Like the title says, very basic project for generating a beta distribution to give yourself variable randomness in some Java program.

https://en.wikipedia.org/wiki/Beta_distribution

https://imgur.com/EOkLPBF

Example usage in a program would be:

// Define the ProbabilityCalculator
mBetaDistAlphaParam = // Some number 0.1 to 5 tend to be reasonable (read article above and tailor toward your needs)
mBetaDistBetaParam  = // Some number 0.1 to 5 tend to be reasonable (read article above and tailor toward your needs)
mFlashProbability = new ProbabilityCalculator( mBetaDistAlphaParam, mBetaDistBetaParam );

// mHalfPeriod is 1/2 * 1/freq of average signal.  Sets the centerpoint for randomness
// Meant to wander from -3.5 sigma to 3.5 sigma at max.
long currentTime = System.currentTimeMillis();
long lastTime = currentTime;
float deltaTime;
float average = mHalfPeriod;
float stDev = mHalfPeriod / 4;
int flashChangeDev = flashRandom.nextInt( 9996 ) + 2;
float currentPeriod = average + stDev * mFlashProbability.cumulativeDistFunctionValuesBeta[ flashChangeDev ];
boolean strobeOn = false;

// isOn is externally defined boolean to escape the loop if need be
while( isOn ){
    currentTime = System.currentTimeMillis();
    deltaTime = ( currentTime - lastTime ) * 0.001f;
    if( deltaTime > currentPeriod ){
        lastTime = currentTime;
        if( !strobeOn ){
            // Turn on whatever turns on
        } else {
            // Turn off whatever turns off
        }
        strobeOn = !strobeOn;
        average = mHalfPeriod;
        stDev = mHalfPeriod / 4;
        flashChangeDev = flashRandom.nextInt( 9996 ) + 2;
        currentPeriod = average + stDev * mFlashProbability.cumulativeDistFunctionValuesBeta[ flashChangeDev ];
    }
}
