package dio.budgeting.application.output;

import dio.budgeting.domain.Category;

public record CategorySummaryOutput(Category category, int transactionCount, long totalAmountInCents){}
