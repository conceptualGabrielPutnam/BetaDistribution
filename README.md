# BetaDistribution
Quick Java project for generating a Beta Distribution using a lookup table.

Like the title says, very basic project for generating a beta distribution to give yourself variable randomness in some Java program.

https://en.wikipedia.org/wiki/Beta_distribution

[https://imgur.com/EOkLPBF](https://imgur.com/EOkLPBF)

Example usage in a program would be:

// Define the ProbabilityCalculator
mBetaDistAlphaParam = // Some number 0.1 to 5 tend to be reasonable (read article above and tailor toward your needs)

mBetaDistBetaParam  = // Some number 0.1 to 5 tend to be reasonable (read article above and tailor toward your needs)

mFlashProbability = new ProbabilityCalculator( mBetaDistAlphaParam, mBetaDistBetaParam );

// Call the Beta Dist.

Random flashRandom = new Random();

int flashChangeDev = flashRandom.nextInt( 9996 ) + 2;

float randomValue = mFlashProbability.cumulativeDistFunctionValuesBeta[ flashChangeDev ];
