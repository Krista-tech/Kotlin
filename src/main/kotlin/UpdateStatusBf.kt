import java.time.Instant
import java.time.temporal.ChronoUnit

class UpdateStatusBf {

    open val time: Instant = Instant.now().plus(4, ChronoUnit.DAYS)
    operator fun invoke(id: String, status: Status, subscription: Subscription?): Any? {

        if (id == subscription?.id) {
            subscription.startDate = time
        }
        if (subscription != null && subscription.endDate < Instant.now()) {
            subscription.purchaseStatus = PurchaseStatus.CANCELED

        }

        if (subscription?.purchaseStatus == PurchaseStatus.ACTIVE) {
            Status.ACCEPTED
        } else {
            Status.REJECTED_INELIGIBLE
        }
        return Pair(status, subscription)
    }

    enum class Status {
        ACCEPTED,
        REJECTED_NOT_FOUND,
        REJECTED_INELIGIBLE,
        REJECTED_REGISTERED_TO_ANOTHER_ACCOUNT
    }

    enum class PurchaseStatus {
        ACTIVE,
        CANCELED,
        NOT_FOUND
    }

    data class Subscription(
        val id: String,
        var startDate: Instant,
        val endDate: Instant,
        var purchaseStatus: PurchaseStatus
    )
}
