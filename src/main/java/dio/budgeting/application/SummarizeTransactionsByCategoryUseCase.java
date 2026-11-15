package dio.budgeting.application;

import dio.budgeting.application.output.CategorySummaryOutput;
import dio.budgeting.domain.Category;
import dio.budgeting.domain.Transaction;
import dio.budgeting.domain.TransactionRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

@Service
public class SummarizeTransactionsByCategoryUseCase {
    private final TransactionRepository transactionRepository;

    public SummarizeTransactionsByCategoryUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Tool(name = "summarize-transactions-by-category", description = "Calcula o total gasto em centavos e a quantidade de transações de uma categoria")
    public CategorySummaryOutput execute(@ToolParam(description = "Categoria da transação") Category category) {
        var transactions = transactionRepository.findAllByCategory(category);
        long totalAmountInCents = transactions.stream().mapToLong(Transaction::getAmount).sum();
        return new CategorySummaryOutput(category, transactions.size(), totalAmountInCents);
    }
}
