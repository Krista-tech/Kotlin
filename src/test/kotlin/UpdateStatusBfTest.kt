import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import java.time.DayOfWeek
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalUnit


class UpdateStatusBfTest : StringSpec() {

    private val activeStatus = UpdateStatusBf.Status.ACCEPTED
    private val rejectStatus = UpdateStatusBf.Status.REJECTED_INELIGIBLE
    private val subscription = UpdateStatusBf.Subscription("12345", Instant.EPOCH, Instant.MAX, UpdateStatusBf.PurchaseStatus.ACTIVE)
    private val endDate = Instant.now().minus(4, ChronoUnit.DAYS)

    //private val startDate = Instant.now()
    private val finishedSubscription =
        UpdateStatusBf.Subscription("12", Instant.EPOCH, endDate, UpdateStatusBf.PurchaseStatus.ACTIVE)

    private val nullSub = null
    private val id = "12345"


    init {
        val bf = UpdateStatusBf()


        """1. When input is valid and not empty, should return Status.ACCEPTED""" {

            val result = bf(id, activeStatus, subscription)
            result shouldBe Pair(
                UpdateStatusBf.Status.ACCEPTED,
                UpdateStatusBf.Subscription("12345", startDate = bf.time, Instant.MAX, UpdateStatusBf.PurchaseStatus.ACTIVE)
            )
        }

        """2. When mediaList is empty in input, 
            should return Status.REJECTED_MEDIA_NOT_FOUND""" {

            val result = bf(id, activeStatus, nullSub)
            result shouldBe Pair(UpdateStatusBf.Status.ACCEPTED, null)
        }

        """if id == subscription id then update startDate""" {

            val result = bf(id, activeStatus, subscription)
            result shouldBe Pair(
                UpdateStatusBf.Status.ACCEPTED,
                UpdateStatusBf.Subscription("12345", startDate = bf.time, Instant.MAX, UpdateStatusBf.PurchaseStatus.ACTIVE)
            )
        }

        """if subscription end date => before today, then set Subscription status = cancel""" {

            val result = bf(id, activeStatus, finishedSubscription)
            result shouldBe Pair(
                UpdateStatusBf.Status.ACCEPTED,
                UpdateStatusBf.Subscription("12", Instant.EPOCH, endDate, UpdateStatusBf.PurchaseStatus.CANCELED)
            )
        }
    }
}