public class ProbabilityCalculator {
    public float[] cumulativeDistFunctionValuesBeta = new float[ 10000 ];

    public ProbabilityCalculator( float alpha, float beta ){
        constructBetaDistribution( alpha, beta );
    }

    public void constructBetaDistribution( float alpha, float beta ){
        float[] evenlySpacedDeviations    = new float[10000];
        float[] evenlySpacedProbabilities = new float[10000];
        float[] evenlySpacedProbabilitySums = new float[10000];
        float[] newBetaDistribution = new float[10000];
        float x = 0.0001f;
        float alpham1 = alpha - 1.0f;
        float betam1  = beta - 1.0f;
        float sum = 0;

        // First calculate the evenly spaced probabilities (ignore endpoints as they cause singular issues)
        for( int i = 1; i < evenlySpacedProbabilities.length-1; i++ ){
            evenlySpacedDeviations[i] = -3.5f + x*7.0f;
            evenlySpacedProbabilities[i] = ( float )( Math.pow( x, alpham1 ) * Math.pow( 1-x, betam1 ) );
            sum += evenlySpacedProbabilities[i];
            evenlySpacedProbabilitySums[i] = sum;
            x += 0.0001f;
        }

        // Normalize so all the probabilities sum to 1   1/( 3.5-(-3.5) ) * 10000
        float normMult = 1.0f / sum;
        for( int i = 1; i < evenlySpacedProbabilities.length-1; i++ ){
            evenlySpacedProbabilitySums[i] *= normMult;
        }

        // Now build a cumulative distribution table
        // Move through the list, check whether the probability has risen above the tolerance ( 1/1000 )
        int currentIndex = 0;     // Index into the evenly spaced probabilities
        float targetValue = 0.0f; // i * 0.0001f
        for( int i = 1; i < newBetaDistribution.length - 1; i++ ){
            targetValue = i * 0.0001f;
            if( targetValue < evenlySpacedProbabilitySums[ currentIndex ] ){
                newBetaDistribution[i] = evenlySpacedDeviations[ currentIndex ];
            } else {
                while( targetValue > evenlySpacedProbabilitySums[ currentIndex ] ){
                    currentIndex++;
                }
                newBetaDistribution[i] = evenlySpacedDeviations[ currentIndex ];
            }
        }
        cumulativeDistFunctionValuesBeta = newBetaDistribution;
    }
}
